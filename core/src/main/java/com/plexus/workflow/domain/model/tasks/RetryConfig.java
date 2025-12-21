package com.plexus.workflow.domain.model.tasks;

import java.util.List;

public class RetryConfig {

  private int maxRetries;
  private int retryDelayMs;
  private String backoffStrategy;
  private List<Integer> retryOnStatusCodes;
  private Boolean retryOnNetworkError;
  private Boolean expectJson;

  public int getMaxRetries() {
    return maxRetries;
  }

  public int getRetryDelayMs() {
    return retryDelayMs;
  }

  public String getBackoffStrategy() {
    return backoffStrategy;
  }

  public List<Integer> getRetryOnStatusCodes() {
    return retryOnStatusCodes;
  }

  public Boolean getRetryOnNetworkError() {
    return retryOnNetworkError;
  }

  public Boolean getExpectJson() {
    return expectJson;
  }

  public void setMaxRetries(int maxRetries) {
    this.maxRetries = maxRetries;
  }

  public void setRetryDelayMs(int retryDelayMs) {
    this.retryDelayMs = retryDelayMs;
  }

  public void setBackoffStrategy(String backoffStrategy) {
    this.backoffStrategy = backoffStrategy;
  }

  public void setRetryOnStatusCodes(List<Integer> retryOnStatusCodes) {
    this.retryOnStatusCodes = retryOnStatusCodes;
  }

  public void setRetryOnNetworkError(Boolean retryOnNetworkError) {
    this.retryOnNetworkError = retryOnNetworkError;
  }

  public void setExpectJson(Boolean expectJson) {
    this.expectJson = expectJson;
  }
}
