package com.zettamine.accounts.services;

import com.zettamine.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {
	CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);

}
