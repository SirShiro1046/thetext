package org.codelab.thetext.services.impls;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.codelab.thetext.persistence.entities.Message;
import org.codelab.thetext.persistence.entities.User;
import org.codelab.thetext.persistence.repositories.MessageRepository;
import org.codelab.thetext.services.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
@Log4j2
public class MessageServiceImpl implements MessageService {

    private  final MessageRepository messageRepository;
    @Override
    public Message save(Message message) {
        log.info("Guardando mensaje: {}", message);
        return messageRepository.save(message);
    }

    @Override
    public Optional<Message> findById(Long id) {
        log.info("Buscando mensaje por id: {}", id);
        return messageRepository.findById(id);
    }

    @Override
    public List<Message> findAll() {
        log.info("Buscando todos los mensajes");
        return messageRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        log.info("Borrando mensaje por id: {}", id);
        messageRepository.deleteById(id);

    }

    @Override
    public Long count() {
        log.info("Contando mensajes");
        return messageRepository.count();
    }

    @Override
    public List<Message> findBySender(User sender) {
        log.info("Buscando mensajes por remitente: {}", sender);
        return messageRepository.findBySender(sender);
    }

    @Override
    public List<Message> findByReceiver(User receiver) {
        log.info("Buscando mensajes por receptor: {}", receiver);
        return messageRepository.findByReceiver(receiver);
    }
}
