package com.moj.codetest.command;


import com.moj.codetest.model.Vehicle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class LeftCmdTest {

    @Mock
    private Vehicle vehicle;

    private LeftCmd cmd;

    @Before
    public void setup() {
        cmd = new LeftCmd();
    }

    @Test
    public void testExecute_callsRotateLeft() {
        cmd.execute(vehicle);

        verify(vehicle).rotateLeft();
        verifyNoMoreInteractions(vehicle);
    }
}
