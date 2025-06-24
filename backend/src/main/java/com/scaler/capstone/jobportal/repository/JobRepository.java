package com.scaler.capstone.jobportal.repository;

import com.scaler.capstone.jobportal.enums.ApplicationStatus;
import com.scaler.capstone.jobportal.model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Repository interface for accessing and managing Job documents in MongoDB.
 * Extends {@link MongoRepository} to provide CRUD operations.
 */
public interface JobRepository extends MongoRepository<Job, Long> {

    /**
     * Finds jobs where the given applicant has applied and the application status matches the specified status.
     *
     * @param applicantId the ID of the applicant
     * @param applicationStatus the application status to filter by
     * @return a list of jobs that the applicant has applied to with the specified status
     */
    @Query("{ 'applicants': { $elemMatch: { 'applicantId': ?0, 'applicationStatus': ?1 } } }")
    List<Job> findByApplicantIdAndApplicationStatus(Long applicantId, ApplicationStatus applicationStatus);

    /**
     * Finds all jobs posted by the specified user.
     *
     * @param postedBy the ID of the user who posted the jobs
     * @return a list of jobs posted by the specified user
     */
    List<Job> findByPostedBy(Long postedBy);
}
