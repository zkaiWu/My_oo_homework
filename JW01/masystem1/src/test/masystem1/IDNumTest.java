package test.masystem1;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import masystem1.IDNum;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;




public class IDNumTest {
	
	@ParameterizedTest
	@CsvFileSource(resources = "./data.csv")
	public void testIDNum(String temp) {
	
		System.out.println("haha");
//		assertEquals(test.masystem1.IDNum.checkIDNum(temp),masystem1.IDNum.checkIDNum(temp));
		assertEquals(false,masystem1.IDNum.checkIDNum(temp));
	}

}
