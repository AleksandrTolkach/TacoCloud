package tacos.integration;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "numberChannel")
public interface NumberFileWriterGateway {

  void writeToFile(@Header(FileHeaders.FILENAME) String fileName, String data);
}
