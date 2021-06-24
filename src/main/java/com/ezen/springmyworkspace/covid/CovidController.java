package com.ezen.springmyworkspace.covid;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CovidController {

	private CovidRepository repo;

	@Autowired
	public CovidController(CovidRepository repo) {
		this.repo = repo;
	}

	@GetMapping(value = "/covid")
	public List<Covid> getCovidList() {
		return repo.findAll(Sort.by("id").descending());
	}

	@GetMapping(value = "/covid/paging")
	public Page<Covid> getCovidListPaging(@RequestParam int page, @RequestParam int size) {
		return repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
	}

	@PostMapping(value = "/covid")
	public Covid addCovid(@RequestBody Covid covid, HttpServletResponse res) {
		if (covid.getName() == null || covid.getName().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if (covid.getLocation() == null || covid.getLocation().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		covid.setCreatedTime(new Date().getTime());

		return repo.save(covid);
	}

	@GetMapping(value = "/covid/{id}")
	public Covid getCovid(@PathVariable int id, HttpServletResponse res) {
		Optional<Covid> covid = repo.findById(id);
		if (covid.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return covid.get();
	}

	@DeleteMapping(value = "/covid/{id}")
	public boolean removeCovid(@PathVariable int id, HttpServletResponse res) {
		Optional<Covid> covid = repo.findById(id);
		if (covid.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}
		repo.deleteById(id);
		return true;
	}

	@PutMapping(value = "/covid/{id}")
	public Covid modifyCovid(@PathVariable int id, @RequestBody Covid covid, HttpServletResponse res) {

		Optional<Covid> findedcovid = repo.findById(id);

		if (findedcovid.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		if (covid.getName() == null || covid.getName().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		Covid toUpdateCovid = findedcovid.get();
		toUpdateCovid.setName(covid.getName());
		toUpdateCovid.setLocation(covid.getLocation());

		return repo.save(toUpdateCovid);
	}
}