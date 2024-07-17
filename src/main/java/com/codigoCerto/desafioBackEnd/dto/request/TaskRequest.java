package com.codigoCerto.desafioBackEnd.dto.request;

import com.codigoCerto.desafioBackEnd.enums.Status;

public record TaskRequest(String name, String description, Status status) {}
