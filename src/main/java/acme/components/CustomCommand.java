/*
 * CustomCommand.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.components;

import acme.framework.components.Command;

public enum CustomCommand implements Command {

	DISPLAY, LIST_TOP, LIST_MINE, LIST_DUTIES, LIST_THREAD, LIST_NOT_MINE, ADD_USER_LIST, LIST_USERS_THREAD, ADD_USER_THREAD, DELETE_USER_THREAD

}
