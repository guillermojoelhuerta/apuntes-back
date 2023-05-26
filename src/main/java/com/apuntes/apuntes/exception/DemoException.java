package com.apuntes.apuntes.exception;

public class DemoException extends Exception {

   private ExceptionDetails details;

   public DemoException(String message, ExceptionDetails details, Throwable e) {
      super(message, e);
      setDetails(details);
   }

   public DemoException(String message, ExceptionDetails details) {
      super(message);
      setDetails(details);
   }

   public ExceptionDetails getDetails() {
      return details;
   }
   public void setDetails(ExceptionDetails details) {
      this.details = details;
   }
}
