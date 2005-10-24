/*
 * Copyright (c) 1999-2004 Sourceforge JACOB Project.
 * All rights reserved. Originator: Dan Adler (http://danadler.com).
 * Get more information about JACOB at http://sourceforge.net/projects/jacob-project
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package com.jacob.com;

/**
 * thrown in util.cpp
 */
public class WrongThreadException extends RuntimeException {
    /**
	 * identifier generated by Eclipse
	 */
	private static final long serialVersionUID = 6308780364980228692L;

	/**
     * standard 0 arg constructor with no message
     *
     */
    public WrongThreadException() {
        super();
    }

    /**
     * standard constructor with a string message
     * @param s
     */
    public WrongThreadException(String s) {
        super(s);
    }
}