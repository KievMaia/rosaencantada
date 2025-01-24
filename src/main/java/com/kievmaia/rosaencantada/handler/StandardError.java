package com.kievmaia.rosaencantada.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class StandardError {
  private int status;
  private String error;
  private String message;
  private String timestamp;
}
