package org.example.listingservice.services.communication;

import org.example.listingservice.converter.Converter;
import lombok.RequiredArgsConstructor;
import org.example.listingservice.constant.MessageKeys;
import org.example.listingservice.dtos.CommunicationDTO;
import org.example.listingservice.exceptions.DataNotFoundException;
import org.example.listingservice.models.Building;
import org.example.listingservice.models.Communication;
import org.example.listingservice.models.User;
import org.example.listingservice.repositories.BuildingRepository;
import org.example.listingservice.repositories.CommunicationRepository;
import org.example.listingservice.repositories.UserRepository;
import org.example.listingservice.responses.communication.CommunicationListResponse;
import org.example.listingservice.responses.communication.CommunicationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunicationService implements ICommunicationService{
    private final CommunicationRepository communicationRepository;
    private final Converter converter;
    private final BuildingRepository buildingRepository;
    private final UserRepository userRepository;

    @Override
    public CommunicationListResponse getAllByKeyWord(String keyword, int page, int limit) {
        int totalPages = 0;
        PageRequest  pageRequest = PageRequest.of(page,limit);
       Page<Communication> pages = communicationRepository.findByKeyword(keyword,pageRequest);
       totalPages = pages.getTotalPages();
       List<Communication> communications = pages.getContent();
       List<CommunicationResponse> communicationResponses = communications
               .stream().map(CommunicationResponse::fromCommunication).toList();

        return CommunicationListResponse.builder()
                       .totalPages(totalPages)
                       .communicationResponseList(communicationResponses)
                       .build();

    }

    @Override
    public CommunicationListResponse getAllByUserId(Long id,String keyword, int page, int limit) throws DataNotFoundException {
        int totalPages = 0;
//        Sort.Direction direction = Sort.Direction.ASC;
//        Sort sort = sort = Sort.by(direction);
        PageRequest  pageRequest = PageRequest.of(page,limit);

        User existingUser = userRepository.findById(id).orElseThrow(()->
             new DataNotFoundException(MessageKeys.DATA_NOT_FOUND+" user"));

        Page<Communication> pages = communicationRepository.findByKeywordAndSaler_IdOrBuyerRenter_Id(keyword,pageRequest,existingUser.getId(),existingUser.getId());
        totalPages = pages.getTotalPages();
        List<Communication> communications = pages.getContent();
        List<CommunicationResponse> communicationResponses = communications
                .stream().map(CommunicationResponse::fromCommunication).toList();
        return CommunicationListResponse.builder()
                .totalPages(totalPages)
                .communicationResponseList(communicationResponses)
                .build();
    }

    @Override
    public void deleteCommunication(Long id) throws DataNotFoundException {
        Communication existingCommu = communicationRepository.findById(id)
                .orElseThrow(()->new DataNotFoundException(MessageKeys.DATA_NOT_FOUND));
        communicationRepository.delete(existingCommu);
    }

    @Override
    public CommunicationResponse createOrUpdate(CommunicationDTO dto) throws DataNotFoundException {
        Communication updateCommunication;

        updateCommunication = Communication.builder()
                .phone(dto.getPhone())
                .note(dto.getNote())
                .build();


        Building existingBuilding = buildingRepository.findById(dto.getBuildingId()).orElseThrow(()
                ->new DataNotFoundException(MessageKeys.DATA_NOT_FOUND));
        User buyer = userRepository.findById(dto.getBuyerId()).orElseThrow(()
                ->new DataNotFoundException(MessageKeys.DATA_NOT_FOUND));

        User owner = existingBuilding.getUser();
        updateCommunication.setBuilding(existingBuilding);
        updateCommunication.setSaler(owner);
        updateCommunication.setBuyerRenter(buyer);
        Communication savedCommunication = communicationRepository
                .saveAndFlush(updateCommunication);

        return CommunicationResponse
                .fromCommunication(savedCommunication);
    }

    @Override
    public CommunicationListResponse getAllByBuildingId(Long id) throws DataNotFoundException {
        Building existingBuilding = buildingRepository.findById(id).orElseThrow(()
                ->new DataNotFoundException(MessageKeys.DATA_NOT_FOUND));

        List<Communication> communications = communicationRepository.findAllByBuilding_Id(id);
       List<CommunicationResponse> communicationResponses= communications.stream().map(CommunicationResponse::fromCommunication).toList();
        return CommunicationListResponse.builder()
                .communicationResponseList(communicationResponses)
                .build();
    }

    @Override
    public CommunicationListResponse getAllBySaler_IDOrUserId(Long id, String buildingName, int page, int limit) {
        int totalPages = 0;
        PageRequest pageRequest = PageRequest.of(page,limit);
        Page<Communication> pages = communicationRepository.findAllBySaler_IdOrBuyerRenter_IdOrBuilding_Name(id,id,buildingName,pageRequest);
        totalPages = pages.getTotalPages();
        List<Communication> communications = pages.getContent();
        List<CommunicationResponse> communicationResponses = communications
                .stream().map(CommunicationResponse::fromCommunication).toList();
        return CommunicationListResponse.builder()
                .totalPages(totalPages)
                .communicationResponseList(communicationResponses)
                .build();
    }
}
