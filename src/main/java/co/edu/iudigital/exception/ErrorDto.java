package co.edu.iudigital.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ErrorDto implements Serializable{
	
    private static final long serialVersionUID = 1L;

    private String error;

    private String message;

    private int status;

    private LocalDateTime date;

    /**
     * Obtiene nuevo error
     *
     * @param error String Nombre error HTTP
     * @param message String Mensaje personalizado del error HTTP
     * @param status int Codigo error HTTP
     * @return
     */
    public static ErrorDto getErrorDto(String error, String message, int status) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError(error);
        errorDto.setMessage(message);
        errorDto.setStatus(status);
        errorDto.setDate(LocalDateTime.now());
        return errorDto;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
