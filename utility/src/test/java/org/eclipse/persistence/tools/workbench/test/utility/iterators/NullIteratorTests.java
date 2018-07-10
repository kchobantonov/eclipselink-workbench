/*
 * Copyright (c) 1998, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */

// Contributors:
//     Oracle - initial API and implementation from Oracle TopLink
package org.eclipse.persistence.tools.workbench.test.utility.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.persistence.tools.workbench.utility.iterators.NullIterator;

public class NullIteratorTests extends TestCase {

    public static Test suite() {
        return new TestSuite(NullIteratorTests.class);
    }

    public NullIteratorTests(String name) {
        super(name);
    }

    public void testHasNext() {
        int i = 0;
        for (Iterator stream = NullIterator.instance(); stream.hasNext(); ) {
            stream.next();
            i++;
        }
        assertEquals(0, i);
    }

    public void testNext() {
        for (Iterator stream = NullIterator.instance(); stream.hasNext(); ) {
            fail("bogus element: " + stream.next());
        }
    }

    public void testNoSuchElementException() {
        boolean exCaught = false;
        Iterator stream = NullIterator.instance();
        Object element = null;
        while (stream.hasNext()) {
            element = stream.next();
        }
        try {
            element = stream.next();
        } catch (NoSuchElementException ex) {
            exCaught = true;
        }
        assertTrue("NoSuchElementException not thrown: " + element, exCaught);
    }

    public void testUnsupportedOperationException() {
        boolean exCaught = false;
        try {
            NullIterator.instance().remove();
        } catch (UnsupportedOperationException ex) {
            exCaught = true;
        }
        assertTrue("UnsupportedOperationException not thrown", exCaught);
    }

}
