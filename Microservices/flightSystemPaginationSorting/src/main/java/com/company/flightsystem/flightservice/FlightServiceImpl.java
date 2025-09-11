package com.company.flightsystem.flightservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.flightsystem.entities.Flight;
import com.company.flightsystem.flightrepository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService{
	
	@Autowired 
	FlightRepository flightRepository;

	@Override
	public Flight addFlightDetails(Flight flight) {
		// TODO Auto-generated method stub
		Flight flightSaved =  flightRepository.save(flight);
		
		System.out.println("Flight details are saved");
		return flightSaved;
	}

	@Override
	public Flight updateFlightDetails(Integer flightNumber, Flight flight) {
		// TODO Auto-generated method stub
		Flight flightUpdated = null;
		if(flightRepository.findById(flightNumber).get()!=null)
		{
			flightUpdated = flightRepository.save(flight);
		}
		else
		{
			flightUpdated = null;
		}
		return flightUpdated;
	}

	@Override
	public Flight getFlightDetails(Integer flightNumber) {
		// TODO Auto-generated method stub
		
		return flightRepository.findById(flightNumber).get();
		
	}

	@Override
	public List<Flight> getAllFlightsDetails() {
		// TODO Auto-generated method stub
		
		return flightRepository.findAll();
		
	}

	@Override
	public String deleteFlightDetails(Integer flightNumber) {
		// TODO Auto-generated method stub
		
		Flight flightDelete = flightRepository.findById(flightNumber).get();
		String message ;
		if(flightDelete!=null)
		{
			flightRepository.delete(flightDelete);
			message = " flight details deleted successfully";
			
		}
		else
		{
			message = " flight details could not be deleted";
		}
		return message;
	}

	@Override
	public Page<Flight> getAllFlights(Pageable pageable) {
		// TODO Auto-generated method stub
		
		return flightRepository.findAll(pageable);
		
	}

}
