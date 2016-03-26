// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
package org.kududb.client;

import org.junit.Test;
import org.kududb.client.Status;

import static org.junit.Assert.*;

public class TestStatus {

  @Test
  public void testOKStatus() {
    Status s = Status.OK();
    assertTrue(s.ok());
    assertFalse(s.isNotAuthorized());
    assertEquals(-1, s.getPosixCode());
    assertEquals("OK", s.toString());
  }

  @Test
  public void testStatusNonPosix() {
    Status s = Status.Aborted("foo");
    assertFalse(s.ok());
    assertTrue(s.isAborted());
    assertEquals("ABORTED", s.getCodeName());
    assertEquals("foo", s.getMessage());
    assertEquals(-1, s.getPosixCode());
    assertEquals("Aborted: foo", s.toString());
  }

  @Test
  public void testPosixCode() {
    Status s = Status.NotFound("File not found", 2);
    assertFalse(s.ok());
    assertFalse(s.isAborted());
    assertTrue(s.isNotFound());
    assertEquals(2, s.getPosixCode());
    assertEquals("Not found: File not found (error 2)", s.toString());
  }
}
