package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleMinReportDTO;
import com.devsuperior.dsmeta.dto.SaleMinSumaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinReportDTO> searchReport(String minDate, String maxDate, String name, Pageable pageable) {

		LocalDate dtFinal  = instanciaDtFinal(maxDate);
		LocalDate dtInicial = instanciaDtInicial(minDate, dtFinal);

		Page<SaleMinReportDTO>  result = repository.searchReport(dtInicial, dtFinal, name, pageable);
		return result;
	}

	public Page<SaleMinSumaryDTO> searchSumary(String minDate, String maxDate,  Pageable pageable) {

		LocalDate dtFinal  = instanciaDtFinal(maxDate);
		LocalDate dtInicial = instanciaDtInicial(minDate, dtFinal);

		Page<SaleMinSumaryDTO>  result = repository.searchSumary(dtInicial, dtFinal,  pageable);
		return result;
	}

	private static LocalDate instanciaDtFinal(String maxDate){

		if(maxDate.isBlank())
			return   LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		return  LocalDate.parse(maxDate);
	}

	private static LocalDate instanciaDtInicial(String minDate, LocalDate dtFinal){

		if(minDate.isBlank())
			return dtFinal.minusYears(1L);

		return LocalDate.parse(minDate);
	}
}
