package pl.mzuchnik.complaint.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplaintCounterTest {


    @Test
    void shouldReturnEmptyComplaintCounterWithCounterEquals1() {
        ComplaintCounter complaintCounter = ComplaintCounter.createNew();

        assertEquals(1, complaintCounter.counter());
    }

    @Test
    void shouldReturnNewInstanceAfterIncrement(){
        ComplaintCounter complaintCounter = ComplaintCounter.createNew();

        ComplaintCounter newInstance = complaintCounter.increment();

        assertNotEquals(newInstance, complaintCounter);
    }

    @Test
    void shouldReturnNewInstanceAfterIncrementWithCounterEquals2() {
        ComplaintCounter complaintCounter = ComplaintCounter.createNew();

        ComplaintCounter newInstance = complaintCounter.increment();

        assertEquals(2, newInstance.counter());
    }
}