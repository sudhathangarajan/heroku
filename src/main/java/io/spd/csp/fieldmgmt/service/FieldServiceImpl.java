package io.spd.csp.fieldmgmt.service;

import io.spd.csp.fieldmgmt.data.entity.UserEntity;
import io.spd.csp.fieldmgmt.data.repo.InspectionRepository;
import io.spd.csp.fieldmgmt.data.repo.PartRepository;
import io.spd.csp.fieldmgmt.data.repo.ReservationRepository;
import io.spd.csp.fieldmgmt.data.repo.SitePartRepository;
import io.spd.csp.fieldmgmt.data.repo.SiteRepository;
import io.spd.csp.fieldmgmt.data.repo.SparePartRepository;
import io.spd.csp.fieldmgmt.data.repo.UserRepository;
import io.spd.csp.fieldmgmt.dto.*;
import io.spd.csp.fieldmgmt.mapper.InspectionMapperFactory;
import io.spd.csp.fieldmgmt.mapper.PartMapperFactory;
import io.spd.csp.fieldmgmt.mapper.SiteMapperFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
record FieldServiceImpl(InspectionRepository inspectionRepository, InspectionMapperFactory inspectionMapper,
                        UserRepository userRepository, ReservationRepository reservationRepository,
                        PartRepository partRepository, SiteRepository siteRepository, SiteMapperFactory siteMapper,
                        SparePartRepository sparePartRepository, SitePartRepository sitePartRepository,
                        PartMapperFactory partMapper)
        implements FieldService {

    @Override
    public Flux<InspectionDto> getInspections(String username, LocalDate localDate) {
        return userRepository.findByUsername(username).map(UserEntity::getId)
                .flatMapMany(id -> inspectionRepository.findScheduledInspections(id, localDate.atStartOfDay(),
                        localDate.atTime(LocalTime.MAX)))
                .map(inspectionMapper.b2a());
    }

    @Override
    public Flux<InspectionDto> getAllInspectionsForToday(LocalDate localDate) {
        return userRepository.findByRole(Role.TECHNICIAN).log()
                .map(UserEntity::getId)
                .flatMap(id -> inspectionRepository.findScheduledInspections(id, localDate.atStartOfDay(),
                        localDate.atTime(LocalTime.MAX)))
                .map(inspectionMapper.b2a());
    }

    public Mono<InspectionDetailsDto> getInspectionDetails(Integer inspectionId) {
        Mono<List<ReservedPartDto>> reservedParts = reservationRepository.findByInspectionId(inspectionId)
                .flatMap(reservation -> partRepository.findById(reservation.getPartId())
                        .map(part -> new ReservedPartDto(part.getId(), part.getName(), reservation.getQuantity())))
                .collectList();
        return inspectionRepository.findById(inspectionId).map(inspectionMapper.b2a()).zipWith(reservedParts)
                .flatMap(inspection -> {
                    Mono<SiteDto> site = siteRepository.findById(inspection.getT1().siteId()).map(siteMapper.b2a());
                    Flux<SitePartDto> siteParts = site.flatMapMany(s -> sitePartRepository.findBySiteId(s.id()))
                            .flatMap(sp -> partRepository.findById(sp.getPartId()).map(p -> new SitePartDto(sp.getId(),
                                    sp.getSiteId(), p.getId(), sp.getIntegrity())));
                    return site.zipWith(siteParts.collectList()).map(tuple -> new InspectionDetailsDto(
                            inspection.getT1(), tuple.getT1(), inspection.getT2(), tuple.getT2()));
                });
    }

    public Flux<SparePartDto> getSpareParts() {
        return sparePartRepository.findAll().flatMap(sp -> partRepository.findById(sp.getPartId()).map(part ->
                SparePartDto.builder().partId(part.getId()).partName(part.getName()).quantity(sp.getQuantity()).build()));
    }

    public Mono<Boolean> updateInspection(Integer inspectionId, InspectionDto.Status status, String remarks) {
        return inspectionRepository.findById(inspectionId).doOnNext(inspection -> {
            inspection.setStatus(status);
            inspection.setRemarks(remarks);
        }).flatMap(inspectionRepository::save).map(i -> true);
    }
}
