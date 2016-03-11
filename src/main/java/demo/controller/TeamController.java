package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.domain.Team;
import demo.repository.TeamRepository;

@RestController
public class TeamController {

	@Autowired TeamRepository teamRepository;
	
	@RequestMapping("/teams")
	public Iterable<Team> getTeams() {
		return teamRepository.findAll();
	}
/*	
	@RequestMapping("/teams/{name}")
	public Team getTeam(@PathVariable String name){
		System.out.println(name);
		return teamRepository.findByName(name);
	}
*/	
	@RequestMapping("/teams/{location}")
	public Team getLocation(@PathVariable String location){
		System.out.println(location);
		return teamRepository.findByLocation(location);
	}
	
}
