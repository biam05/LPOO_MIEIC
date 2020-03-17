package com.aor.numbers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListDeduplicatorTest {
    private List<Integer> list;
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

    public void setListDup(){
        this.listdup = new ArrayList<>();
        listdup.add(1);
        listdup.add(2);
        listdup.add(4);
        listdup.add(2);
        listdup.add(5);
    }

    @Test
    public void deduplicate() {

        setList();

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(4);
        expected.add(5);

        ListDeduplicator deduplicator = new ListDeduplicator(list);
        List<Integer> distinct = deduplicator.deduplicate(new ListSorter(list));

        assertEquals(expected, distinct);
    }

    @Test
    public void deduplicateDup() {

        setListDup();

        class StubSorter implements IListSorter {
            @Override
            public List<Integer> sort() {
                List<Integer> sorted = new ArrayList<>();
                sorted.add(1);
                sorted.add(2);
                sorted.add(2);
                sorted.add(4);
                return sorted;
            }
        }

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(4);

        ListDeduplicator deduplicator = new ListDeduplicator(listdup);
        List<Integer> distinct = deduplicator.deduplicate(new StubSorter());

        assertEquals(expected, distinct);
    }
}