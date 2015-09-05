/*
 * MapComparator.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.framework.utils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Oleg V. Safonov
 * @version $Id: MapComparator.java,v 1.3 2006/09/22 09:27:26 safonov Exp $
 */
public class MapComparator<K,V> implements Comparator<Map<K,Comparable<V>>> {
    
    public static final String module = MapComparator.class.getName();
    
    private List<K> keys;

    /**
     * Method MapComparator.
     * @param keys List of Map keys to sort on
     */
    public MapComparator(List<K> keys) {
        this.keys = keys;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        return obj.equals(this);
    }

    /**
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
	public int compare(Map<K,Comparable<V>> obj1, Map<K,Comparable<V>> obj2) {
        Map<K,Comparable<V>> map1, map2;
        try {
            map1 = obj1;
            map2 = obj2;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Objects not from the Map interface");
        }

        if (keys == null || keys.size() < 1) {
            throw new IllegalArgumentException("No sort fields defined");
        }

        Iterator<K> i = keys.iterator();
        while (i.hasNext()) {
            // if false will be descending, ie reverse order
            boolean ascending = true;
            K key = i.next();
            if (key instanceof String) {
                String keyStr = (String) key;
                if (keyStr.charAt(0) == '-') {
                    ascending = false;
                    key = (K) keyStr.substring(1);
                } else if (keyStr.charAt(0) == '+') {
                    ascending = true;
                    key = (K) keyStr.substring(1);
                }
            }
            
            Comparable<V> o1 = map1.get(key);
            Comparable<V> o2 = map2.get(key);
            if (o1 == null && o2 == null) {
                continue;
            }
            
            int compareResult = 0;
            if (o1 != null && o2 == null) {
                compareResult = -1;
            }
            if (o1 == null && o2 != null) {
                compareResult = 1;
            }
            
            if (compareResult == 0) {
                try {
                    // the map values in question MUST implement the Comparable interface, if not we'll throw an exception
                    Comparable<V> comp1 = (Comparable<V>) o1;
                    Comparable<V> comp2 = (Comparable<V>) o2;
                    //compareResult = comp1.compareTo((V) o2);
                    boolean compRes = o1 == o2;
                } catch (Exception e) {
                    String errorMessage = "Error sorting list of Maps: " + e.toString();
                    //Debug.logError(e, errorMessage, module);
                    throw new RuntimeException(errorMessage);
                }
            }

            // if zero they are equal, so we carry on to the next key to try and find a difference
            if (compareResult != 0) {
                if (ascending) {
                    return compareResult;
                } else {
                    return -compareResult;
                }
            }
        }
        
        // none of them were different, so they are equal
        return 0;
    }
}
