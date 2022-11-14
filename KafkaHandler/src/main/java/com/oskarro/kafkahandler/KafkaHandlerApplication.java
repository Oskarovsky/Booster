package com.oskarro.kafkahandler;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.Map;

import static java.lang.System.out;

@SpringBootApplication
public class KafkaHandlerApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(KafkaHandlerApplication.class, args);

		final var topic = "oskarro-started";

		final Map<String, Object> config =
				Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
						ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName(),
						ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName(),
						ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

		try (var producer = new KafkaProducer<String, String>(config)) {
			while (true) {
				final var key = "myKey";
				final var value = new Date().toString();
				out.format("Publishing record with value %s%n", value);

				final Callback callback = (metadata, exception) -> {
					out.format("Published with metadata: %s, error: %s%n", metadata, exception);
				};

				// publish the record, handling the metadata in the callback
				producer.send(new ProducerRecord<>(topic, key, value), callback);

				// wait a second before publishing another
				Thread.sleep(1000);
			}
		}
	}
}
