package de.empusa.conway.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CellTest.class, UniverseTest.class, CellFactoryTest.class, RuleTest.class })
public class AllTests {

}
