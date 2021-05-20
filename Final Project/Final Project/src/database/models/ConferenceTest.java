package database.models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import database.core.Database;
import database.core.DatabaseConnector;
import database.repositories.ConferenceMockRepository;
import database.repositories.GradeReviewMockRepository;
import database.repositories.ResearchMockRepository;
import database.repositories.ResearcherMockRepository;
import exceptions.AllocationException;
import exceptions.EntryNotFoundException;
import exceptions.ReviewException;

public class ConferenceTest {
	Conference nonAllocatedConference;
	Conference allocatedButNotReviewedConference;
	Conference allocatedAndReviewedConference;
	
	@Before
	public void setUp() throws Exception {
		ConferenceMockRepository.conferencesRepository = new ArrayList<Conference>();
		GradeReviewMockRepository.gradeReviewRepository = new ArrayList<GradeReview>();
		ResearcherMockRepository.researchersRepository = new ArrayList<Researcher>();
		ResearchMockRepository.researchesRepository = new ArrayList<Research>();
		Database db = DatabaseConnector.connect();
		db.addFakeData();
		nonAllocatedConference = db.findConferenceByShortName("ICSE");
		allocatedButNotReviewedConference = db.findConferenceByShortName("SBES");
		allocatedAndReviewedConference = db.findConferenceByShortName("FSE");
	}
	
	@Test
	public void testAlocatted() throws EntryNotFoundException {
		assertFalse(nonAllocatedConference.isAllocated());
		assertTrue(allocatedButNotReviewedConference.isAllocated());
		assertTrue(allocatedAndReviewedConference.isAllocated());
	}
	
	@Test
	public void testIsFullyReviewed() {
		assertFalse(nonAllocatedConference.isFullyReviewed());
		assertFalse(allocatedButNotReviewedConference.isFullyReviewed());
		assertTrue(allocatedAndReviewedConference.isFullyReviewed());
	}
	
	@Test
	public void testGetAverageResearchesGrades() throws EntryNotFoundException {
		try {
			nonAllocatedConference.getAverageResearchesGrades();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Conference not allocated");
		}
		
		try {
			allocatedButNotReviewedConference.getAverageResearchesGrades();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Conference not fully reviewed");
		}
		
		try {
			assertTrue(allocatedAndReviewedConference.getAverageResearchesGrades().size() > 0);
			
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testGetAcceptedResearches() throws EntryNotFoundException {
		try {
			nonAllocatedConference.getAcceptedResearches();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Conference not allocated");
		}
		
		try {
			allocatedButNotReviewedConference.getAcceptedResearches();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Conference not fully reviewed");
		}
		
		try {
			assertTrue(allocatedAndReviewedConference.getAcceptedResearches().size() > 0);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testGetRejectedResearches() throws EntryNotFoundException {
		try {
			nonAllocatedConference.getRejectedResearches();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Conference not allocated");
		}
		
		try {
			allocatedButNotReviewedConference.getRejectedResearches();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Conference not fully reviewed");
		}
		
		try {
			assertTrue(allocatedAndReviewedConference.getRejectedResearches().size() > 0);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testGetResearchesToAllocate() throws EntryNotFoundException {
		try {
			nonAllocatedConference.getResearchesToAllocate();
			assertTrue(nonAllocatedConference.getResearchesToAllocate().size() > 0);
		} catch (Exception e) {
			fail();
		}
		
		try {
			allocatedButNotReviewedConference.getResearchesToAllocate();
			assertTrue(allocatedButNotReviewedConference.getResearchesToAllocate().size() > 0);
		} catch (Exception e) {
			fail();
		}
		
		try {
			allocatedAndReviewedConference.getResearchesToAllocate();
			assertTrue(allocatedAndReviewedConference.getResearchesToAllocate().size() > 0);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testGetResearchersExceptAuthorAndFromSameOrganization() throws EntryNotFoundException {
		try {
			nonAllocatedConference.getResearchersExceptAuthorAndFromSameOrganization(new Researcher(0, "UFRGS", null, null));
			assertTrue(nonAllocatedConference.getResearchesToAllocate().size() > 0);
		} catch (Exception e) {
			fail();
		}
		
		try {
			allocatedButNotReviewedConference.getResearchersExceptAuthorAndFromSameOrganization(new Researcher(0, "UFRGS", null, null));
			assertTrue(allocatedButNotReviewedConference.getResearchesToAllocate().size() > 0);
		} catch (Exception e) {
			fail();
		}
		
		try {
			allocatedAndReviewedConference.getResearchersExceptAuthorAndFromSameOrganization(new Researcher(0, "UFRGS", null, null));
			assertTrue(allocatedAndReviewedConference.getResearchesToAllocate().size() > 0);
		} catch (Exception e) {
			fail();
		}
	}

}
