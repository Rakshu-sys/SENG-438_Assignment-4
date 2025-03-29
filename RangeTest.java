package org.jfree.data;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Range class in JFreeChart.
 * This test suite verifies the correctness of various Range methods.
 */
public class RangeTest {
    private Range range;

    /**
     * Setup method to initialize a Range instance before each test.
     */
    @Before
    public void setUp() {
        range = new Range(5, 15);
    }

    /**
     * teardown method to clean up after each test.
     */
    @After
    public void tearDown() {
        range = null;
    }

    // --------- Testing contains() Method --------- //
    
    @Test
    public void testContainsWithinRange() {
        assertTrue("Value inside the range should return true", range.contains(10));
    }

    @Test
    public void testContainsAtLowerBound() {
        assertTrue("Value at lower bound should return true", range.contains(5));
    }

    @Test
    public void testContainsAtUpperBound() {
        assertTrue("Value at upper bound should return true", range.contains(15));
    }

    @Test
    public void testContainsBelowRange() {
        assertFalse("Value below the range should return false", range.contains(3));
    }

    @Test
    public void testContainsAboveRange() {
        assertFalse("Value above the range should return false", range.contains(17));
    }

    // --------- Testing combine() Method --------- //
    
    @Test
    public void testCombineTwoValidRanges() {
        Range range1 = new Range(2, 8);
        Range range2 = new Range(10, 20);
        Range combined = Range.combine(range1, range2);
        assertEquals("Combined range should have correct lower bound", 2, combined.getLowerBound(), 0.00001);
        assertEquals("Combined range should have correct upper bound", 20, combined.getUpperBound(), 0.00001);
    }

    @Test
    public void testCombineOverlappingRanges() {
        Range range1 = new Range(5, 12);
        Range range2 = new Range(10, 18);
        Range combined = Range.combine(range1, range2);
        assertEquals("Overlapping ranges should merge correctly", 5, combined.getLowerBound(), 0.00001);
        assertEquals("Overlapping ranges should merge correctly", 18, combined.getUpperBound(), 0.00001);
    }

    @Test
    public void testCombineWithNullRange() {
        assertEquals("Combining with null should return the non-null range", range, Range.combine(range, null));
    }

    @Test
    public void testCombineBothNull() {
        assertNull("Combining two null ranges should return null", Range.combine(null, null));
    }

    @Test
    public void testCombineWithItself() {
        Range combined = Range.combine(range, range);
        assertEquals("Combining range with itself should return the same range", range, combined);
    }

    // --------- Testing expand() Method --------- //
    
    @Test
    public void testExpandRange() {
        Range expanded = Range.expand(range, 0.5, 0.5);
        assertEquals("Expanded lower bound incorrect", 0, expanded.getLowerBound(), 0.00001);
        assertEquals("Expanded upper bound incorrect", 20, expanded.getUpperBound(), 0.00001);
    }

    @Test
    public void testExpandWithZeroMargins() {
        Range expanded = Range.expand(range, 0, 0);
        assertEquals("Expanding with zero margins should return the same range", 5, expanded.getLowerBound(), 0.00001);
        assertEquals("Expanding with zero margins should return the same range", 15, expanded.getUpperBound(), 0.00001);
    }

    @Test
    public void testExpandToIncludeLowerValue() {
        Range expanded = Range.expandToInclude(range, 2);
        assertEquals("Lower bound should expand correctly", 2, expanded.getLowerBound(), 0.00001);
        assertEquals("Upper bound should remain the same", 15, expanded.getUpperBound(), 0.00001);
    }

    // --------- Testing constrain() Method --------- //
    
    @Test
    public void testConstrainWithinRange() {
        assertEquals("Value inside range should return itself", 10, range.constrain(10), 0.00001);
    }

    @Test
    public void testConstrainAtLowerBound() {
        assertEquals("Value at lower bound should return lower bound", 5, range.constrain(5), 0.00001);
    }

    @Test
    public void testConstrainAtUpperBound() {
        assertEquals("Value at upper bound should return upper bound", 15, range.constrain(15), 0.00001);
    }

    @Test
    public void testConstrainBelowRange() {
        assertEquals("Value below range should return lower bound", 5, range.constrain(2), 0.00001);
    }

    @Test
    public void testConstrainAboveRange() {
        assertEquals("Value above range should return upper bound", 15, range.constrain(20), 0.00001);
    }

    // --------- Additional Tests for Coverage --------- //
    
    @Test
    public void testIntersectsFullyOverlapping() {
        assertTrue("Ranges that fully overlap should return true", range.intersects(4, 16));
    }
    
    @Test
    public void testIntersectsNoOverlap() {
        assertFalse("Non-overlapping ranges should return false", range.intersects(20, 30));
    }
    
    @Test
    public void testIntersectsExactBounds() {
        assertTrue("Range that exactly matches should return true", range.intersects(5, 15));
    }
    
    @Test
    public void testIntersectsEnclosedRange() {
        assertTrue("Enclosed range should return true", range.intersects(0, 20));
    }

    @Test
    public void testShiftRangeDownwardWithZeroCrossing() {
        Range shifted = Range.shift(range, -10, true);
        assertEquals("Lower bound should be correctly shifted", -5, shifted.getLowerBound(), 0.00001);
        assertEquals("Upper bound should be correctly shifted", 5, shifted.getUpperBound(), 0.00001);
    }

    @Test
    public void testIsNaNRange() {
        Range nanRange = new Range(Double.NaN, Double.NaN);
        assertTrue("A NaN range should return true", nanRange.isNaNRange());
    }
    
    @Test
    public void testEqualsIdenticalRanges() {
        Range range2 = new Range(5, 15);
        assertTrue("Identical ranges should be equal", range.equals(range2));
    }
    
    @Test
    public void testEqualsDifferentRanges() {
        Range range2 = new Range(10, 20);
        assertFalse("Different ranges should not be equal", range.equals(range2));
    }
    
    @Test
    public void testConstructorWithEqualBounds() {
        Range singlePointRange = new Range(7, 7);
        assertEquals("Lower and upper bounds should be the same", 7, singlePointRange.getLowerBound(), 0.00001);
        assertEquals("Lower and upper bounds should be the same", 7, singlePointRange.getUpperBound(), 0.00001);
    }
    
    @Test
    public void testHashCodeConsistency() {
        Range range2 = new Range(5, 15);
        assertEquals("Equal objects must have the same hash code", range.hashCode(), range2.hashCode());
    }

    // --------- Testing getLowerBound() Method --------- //
    
    @Test
    public void testGetLowerBound() {
        assertEquals("Lower bound should be correct", 5, range.getLowerBound(), 0.00001);
    }

    @Test
    public void testGetLowerBoundWithNegativeRange() {
        Range negativeRange = new Range(-10, -5);
        assertEquals("Lower bound of negative range should be correct", -10, negativeRange.getLowerBound(), 0.00001);
    }

    @Test
    public void testGetUpperBound() {
        assertEquals("Upper bound should be correct", 15, range.getUpperBound(), 0.00001);
    }

    @Test
    public void testGetUpperBoundWithNegativeRange() {
        Range negativeRange = new Range(-10, -5);
        assertEquals("Upper bound of negative range should be correct", -5, negativeRange.getUpperBound(), 0.00001);
    }
    
    @Test
    public void testShiftRangeWithCrossingZero() {
        Range shifted = Range.shift(range, -20, true);
        assertEquals("Shifted lower bound should handle crossing zero", -15, shifted.getLowerBound(), 0.00001);
        assertEquals("Shifted upper bound should handle crossing zero", -5, shifted.getUpperBound(), 0.00001);
    }

    @Test
    public void testCombineWithMultipleRanges() {
        Range range1 = new Range(2, 8);
        Range range2 = new Range(10, 20);
        Range range3 = new Range(5, 15);
        Range combined = Range.combine(Range.combine(range1, range2), range3);
        assertEquals("Combined range should have correct lower bound", 2, combined.getLowerBound(), 0.00001);
        assertEquals("Combined range should have correct upper bound", 20, combined.getUpperBound(), 0.00001);
    }

    @Test
    public void testIntersectsWithSeparatedRanges() {
        assertFalse("Separated ranges should not intersect", range.intersects(20, 30));
        assertFalse("Separated ranges should not intersect", range.intersects(-5, -10));
    }

    
    @Test
    public void testExpandWithExtremeValues() {
        Range expanded = Range.expand(range, Double.MAX_VALUE, Double.MAX_VALUE);
        assertEquals("Expanded lower bound should handle extreme values", Double.NEGATIVE_INFINITY, expanded.getLowerBound(), 0.00001);
        assertEquals("Expanded upper bound should handle extreme values", Double.POSITIVE_INFINITY, expanded.getUpperBound(), 0.00001);
    }
    
    @Test
    public void testExpandToInclude_ValueEqualsLowerBound() {
        Range original = new Range(5.0, 15.0);
        Range result = Range.expandToInclude(original, 5.0);
        assertEquals("Lower bound should stay the same", 5.0, result.getLowerBound(), 0.00001);
        assertEquals("Upper bound should stay the same", 15.0, result.getUpperBound(), 0.00001);
    }

    @Test
    public void testExpandToInclude_ValueEqualsUpperBound() {
        Range original = new Range(5.0, 15.0);
        Range result = Range.expandToInclude(original, 15.0);
        assertEquals("Bounds should not change if value equals upper", 15.0, result.getUpperBound(), 0.00001);
    }

    @Test
    public void testShiftWithoutZeroCrossing_PositiveToNegative() {
        Range original = new Range(1.0, 5.0);
        Range shifted = Range.shift(original, -10.0, false);
        assertEquals("Should stop at 0 due to no zero crossing", 0.0, shifted.getLowerBound(), 0.00001);
        assertEquals("Should stop at 0 due to no zero crossing", 0.0, shifted.getUpperBound(), 0.00001);
    }

    @Test
    public void testShiftWithZeroCrossing_Allowed() {
        Range original = new Range(-5.0, 5.0);
        Range shifted = Range.shift(original, 10.0, true);
        assertEquals("Lower should shift across zero", 5.0, shifted.getLowerBound(), 0.00001);
        assertEquals("Upper should shift across zero", 15.0, shifted.getUpperBound(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testScaleWithNegativeFactor_ShouldThrow() {
        Range base = new Range(2.0, 4.0);
        Range.scale(base, -1.5); // Should throw exception
    }

    @Test
    public void testCombineIgnoringNaN_BothAreNaN() {
        Range r1 = new Range(Double.NaN, Double.NaN);
        Range r2 = new Range(Double.NaN, Double.NaN);
        assertNull("Combining two NaN ranges should return null", Range.combineIgnoringNaN(r1, r2));
    }

    @Test
    public void testCombineIgnoringNaN_OneValidOneNaN() {
        Range valid = new Range(1.0, 3.0);
        Range nan = new Range(Double.NaN, Double.NaN);
        Range result = Range.combineIgnoringNaN(valid, nan);
        assertEquals("Valid range should be returned", valid, result);
    }

   

    @Test
    public void testGetCentralValue_ZeroLengthRange() {
        Range singlePoint = new Range(7.0, 7.0);
        assertEquals("Central value of single point range should be the point itself", 7.0, singlePoint.getCentralValue(), 0.00001);
    }

    @Test
    public void testToString_FormatCheck() {
        Range r = new Range(3.14, 7.77);
        String expected = "Range[3.14,7.77]";
        assertEquals("String representation should match format", expected, r.toString());
    }

}
