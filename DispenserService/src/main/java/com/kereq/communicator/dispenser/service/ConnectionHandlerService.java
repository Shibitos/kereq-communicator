package com.kereq.communicator.dispenser.service;

import com.kereq.communicator.dispenser.entity.ConnectionData;
import com.kereq.communicator.dispenser.repository.ConnectionRepository;
import com.kereq.communicator.dispenser.sender.ConnectionBackendSender;
import com.kereq.communicator.shared.dto.ConnectionEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConnectionHandlerService {

    private final ConnectionBackendSender connectionBackendSender;

    private final ConnectionRepository connectionRepository;

    public ConnectionHandlerService(ConnectionBackendSender connectionBackendSender, ConnectionRepository connectionRepository) {
        this.connectionBackendSender = connectionBackendSender;
        this.connectionRepository = connectionRepository;
    }

    public void handleConnection(ConnectionEventDTO connectionEvent) { //TODO: abstract instead of enums?
        validateEvent(connectionEvent);
        if (!connectionRepository.existsByUserIdAndInstanceId(connectionEvent.getUserId(),
                connectionEvent.getInstanceId())) {
            log.info("Adding new connection ({}: {})", connectionEvent.getInstanceId(), connectionEvent.getUserId());
            ConnectionData connectionData = new ConnectionData(connectionEvent.getUserId(), connectionEvent.getInstanceId());
            connectionRepository.save(connectionData);
            connectionBackendSender.send(connectionEvent); //TODO: send to websocket queue to alert friends?
        }
    }

    public void handleDisconnection(ConnectionEventDTO connectionEvent) { //TODO: abstract instead of enums?
        validateEvent(connectionEvent);
        if (connectionRepository.existsByUserIdAndInstanceId(connectionEvent.getUserId(),
                connectionEvent.getInstanceId())) {
            log.info("Removing connection ({}: {})", connectionEvent.getInstanceId(), connectionEvent.getUserId());
            ConnectionData connectionData = connectionRepository.findByUserIdAndInstanceId(connectionEvent.getUserId(),
                    connectionEvent.getInstanceId());
            if (connectionData == null) {
                throw new NullPointerException(); //TODO: cust
            }
            connectionRepository.delete(connectionData);
            connectionBackendSender.send(connectionEvent);
        }
    }

    private void validateEvent(ConnectionEventDTO connectionEvent) {
        if (connectionEvent == null || connectionEvent.getUserId() == 0L || connectionEvent.getInstanceId() == null) {
            throw new NullPointerException(); //TODO: cust
        }
    }
}
