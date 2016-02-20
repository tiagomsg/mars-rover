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
public class MoveCmdTest {
    @Mock
    private Vehicle vehicle;

    private MoveCmd cmd;

    @Before
    public void setup() {
        cmd = new MoveCmd();
    }

    @Test
    public void testExecute_callsMove() {
        cmd.execute(vehicle);

        verify(vehicle).move();
        verifyNoMoreInteractions(vehicle);
    }
}
