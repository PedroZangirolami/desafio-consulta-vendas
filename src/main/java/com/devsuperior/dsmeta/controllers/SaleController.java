package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleMinReportDTO;
import com.devsuperior.dsmeta.dto.SaleMinSumaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinReportDTO>> getReport(@RequestParam(name = "minDate", defaultValue = "") String minDate,
															@RequestParam(name = "maxDate", defaultValue = "") String maxDate,
															@RequestParam(name = "name", defaultValue = "") String name,
															Pageable pageable)
	{
		Page<SaleMinReportDTO> result = service.searchReport(minDate,maxDate,name,pageable);
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SaleMinSumaryDTO>> getSummary(@RequestParam(name = "minDate", defaultValue = "") String minDate,
															 @RequestParam(name = "maxDate", defaultValue = "") String maxDate,
															 Pageable pageable) {

		Page<SaleMinSumaryDTO> result = service.searchSumary(minDate,maxDate, pageable);
		return ResponseEntity.ok(result);
	}
}
