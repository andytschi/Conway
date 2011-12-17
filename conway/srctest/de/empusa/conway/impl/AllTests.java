package de.empusa.conway.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestCell.class, TestUniverse.class, TestCellFactory.class })
public class AllTests {

}
