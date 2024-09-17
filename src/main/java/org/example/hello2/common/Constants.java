package org.example.hello2.common;

public class Constants {

    public enum ExceptionClass {

        PRODUCT("Product");
        private final String exceptionClass;

        ExceptionClass(String exceptionClass) {
            this.exceptionClass = exceptionClass;
        }

        public String getExceptionClass() {
            return exceptionClass;
        }

        public String toString() {
            return exceptionClass + " Exception ";
        }
    }
}
