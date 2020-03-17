package com.aor.numbers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListAggregatorTest {
    private List<Integer> list;
    private List<Integer> listneg;
    private List<Integer> listdup;

    @Before
    public void setList(){
        this.list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);
        list.add(5);
    }

    public void setListNeg(){
        this.listneg = new ArrayList<>();
        listneg.add(-1);
        listneg.add(-5);
        listneg.add(-4);
    }

    public void setListDup(){
        this.listdup = new ArrayList<>();
        listdup.add(1);
        listdup.add(2);
        listdup.add(4);
        listdup.add(2);
    }

    @Test
    public void sum() {

        setList();

        ListAggregator aggregator = new ListAggregator(list);

        int sum = aggregator.sum();

        assertEquals(14, sum);
    }

    @Test
    public void max() {

        setList();

        ListAggregator aggregator = new ListAggregator(list);

        int sum = aggregator.sum();

        assertEquals(14, sum);
    }

    @Test
    public void maxNeg() {

        setListNeg();

        ListAggregator aggregator = new ListAggregator(listneg);

        int max = aggregator.max();

        assertEquals(-1, max);
    }

    @Test
    public void min() {

        setList();

        ListAggregator aggregator = new ListAggregator(list);

        int min = aggregator.min();

        assertEquals(1, min);
    }



    @Test
    public void distinct() {

        setList();

        class StubDeduplicator implements IListDeduplicator{
            @Override
            public List<Integer> deduplicate() {
                List<Integer> deduplicated = new ArrayList<>();
                deduplicated.add(1);
                deduplicated.add(2);
                deduplicated.add(4);
                deduplicated.add(5);
                return deduplicated;
            }
        }

        ListAggregator aggregator = new ListAggregator(list);

        int distinct = aggregator.distinct(new StubDeduplicator());

        assertEquals(4, distinct);
    }

    @Test
    public void distinctDup() {

        setListDup();

        class StubDeduplicator implements IListDeduplicator{
            @Override
            public List<Integer> deduplicate() {
                List<Integer> deduplicated = new ArrayList<>();
                deduplicated.add(1);
                deduplicated.add(2);
                deduplicated.add(4);
                return deduplicated;
            }
        }

        ListAggregator aggregator = new ListAggregator(listdup);

        int distinct = aggregator.distinct(new StubDeduplicator());

        assertEquals(3, distinct);
    }
}