package com.aor.numbers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListSorterTest {
    private List<Integer> list;
    private List<Integer> listdup;

    @Before
    public void setList(){
        this.list = new ArrayList();
        list.add(3);
        list.add(2);
        list.add(6);
        list.add(1);
        list.add(4);
        list.add(5);
        list.add(7);
    }

    public void setListDup(){
        this.listdup = new ArrayList();
        listdup.add(1);
        listdup.add(2);
        listdup.add(4);
        listdup.add(2);
    }


    @Test
    public void sort() {

        setList();

        List<Integer> expected = new ArrayList();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(7);


        ListSorter sorter = new ListSorter(list);
        List<Integer> sorted = sorter.sort();

        assertEquals(expected, sorted);
    }

    @Test
    public void sortDup() {

        setListDup();

        List<Integer> expected = new ArrayList();
        expected.add(1);
        expected.add(2);
        expected.add(2);
        expected.add(4);

        ListSorter sorter = new ListSorter(listdup);
        List<Integer> sorted = sorter.sort();

        assertEquals(expected, sorted);
    }
}