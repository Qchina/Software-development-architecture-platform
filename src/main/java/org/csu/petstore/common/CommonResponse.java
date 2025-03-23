package org.csu.petstore.common;

import org.csu.petstore.common.ResponseCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {
    private final int status;
    private final String message;
    private T data;


    private CommonResponse(int status,String message){
        this.status=status;
        this.message=message;
    }

    private CommonResponse(int status,String message,T data){
        this.status=status;
        this.message=message;
        this.data=data;
    }
    @JsonIgnore
    public boolean isSuccess(){
        return this.status==ResponseCode.SUCCESS.getCode();
    }
    public static <T>CommonResponse<T> createForSuccess(){
        return new CommonResponse<>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getDesc());
    }

    public static <T>CommonResponse<T> createForSuccessMessage(String message){
        return new CommonResponse<>(ResponseCode.SUCCESS.getCode(),message);
    }

    public static <T>CommonResponse<T> createForSuccess(T data){
        return new CommonResponse<>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getDesc(),data);
    }

    public static <T>CommonResponse<T> createForError(){
        return new CommonResponse<>(ResponseCode.ERROR.getCode(),ResponseCode.SUCCESS.getDesc());
    }

    public static <T>CommonResponse<T> createForError(String message){
        return new CommonResponse<>(ResponseCode.SUCCESS.getCode(),message);
    }

    public static <T>CommonResponse<T> createForSuccess(int code,String message){
        return new CommonResponse<>(code,message);
    }
}
