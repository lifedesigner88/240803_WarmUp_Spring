package org.example.hello2;

import org.junit.jupiter.api.*;

public class TestLifeCycle {

    @BeforeAll
    static void beforeAll(){
        System.out.println("## 🚀 BeforeAll Anotation 호출 ##");
        System.out.println();
    }

    @AfterAll
    static void afterAll(){
        System.out.println("## ✅ AfterAll Anotation 호출 ##");
        System.out.println();
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("## 👇👇BeforeEach Anotation 호출 ##");
        System.out.println();
    }

    @AfterEach
    void afterEach() {
        System.out.println("## 👆👆AfterEach Anotation 호출 ##");
        System.out.println();
        System.out.println();
    }

    @Test
    void test1() {
        System.out.println("## tset1 시작 ##");
        System.out.println();
    }

    @Test
    @DisplayName("Test Case 2!!!")
    void test2() {
        System.out.println("## tset2 시작 ##");
        System.out.println();
    }

    @Test
    @Disabled
    void test3() {
        System.out.println("## tset3 시작 ##");
        System.out.println();
    }

}
