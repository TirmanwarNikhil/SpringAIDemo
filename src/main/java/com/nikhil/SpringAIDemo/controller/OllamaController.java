package com.nikhil.SpringAIDemo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Ollama Chat", description = "Ollama AI Chat API endpoints")
@RequestMapping("/api/ollama")
public class OllamaController {

	private ChatClient chatClient;

	public OllamaController(OllamaChatModel ollamaChatModel) {
		this.chatClient = ChatClient.create(ollamaChatModel);
	}

	@GetMapping("/{message}")
	@Operation(summary = "Get AI Answer", description = "Send a message to the Ollama AI model and get a response")
	@ApiResponse(responseCode = "200", description = "Successful response", content = @Content(mediaType = "application/json", schema = @Schema(type = "string")))
	@ApiResponse(responseCode = "400", description = "Bad Request")
	public String getAnswer(@PathVariable @Parameter(description = "The message to send to Ollama AI") String message) {

		return chatClient.prompt(message).call().content();

	}
}
