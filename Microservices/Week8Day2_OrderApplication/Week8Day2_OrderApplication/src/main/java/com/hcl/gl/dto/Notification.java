package com.hcl.gl.dto;

import java.util.Date;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification 
{
	private int notificationId;
	private String message;
	private long mobileno;
	private String email;
	private Date notificationDate;
}
