package org.example.listingservice.services.communication;

import org.example.listingservice.dtos.CommunicationDTO;
import org.example.listingservice.exceptions.DataNotFoundException;
import org.example.listingservice.responses.communication.CommunicationListResponse;
import org.example.listingservice.responses.communication.CommunicationResponse;

public interface ICommunicationService {
    CommunicationListResponse getAllByKeyWord(String keyword, int page, int limit);
    CommunicationListResponse getAllByUserId(Long id,String keyword, int page, int limit) throws DataNotFoundException;

    void deleteCommunication(Long id) throws DataNotFoundException;

    CommunicationResponse createOrUpdate(CommunicationDTO dto) throws DataNotFoundException;
    CommunicationListResponse getAllByBuildingId(Long id) throws DataNotFoundException;
    CommunicationListResponse getAllBySaler_IDOrUserId(Long id, String buildingName, int page,int limit);
}
