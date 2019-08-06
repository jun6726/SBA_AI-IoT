package com.example.myapplication;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;

public class Calculator {
    //------------------------------------------------------------------------
    // 괄호 까지 존재하는 사칙연사
    //------------------------------------------------------------------------
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static String bracketCalMain(String sNumList) throws Exception{
        //0. ( ) 괄호 갯수 검증
        if (checkBracket(sNumList) == false) return "괄호 () 갯수가 Match되지 않습니다.";

        // 1. 1차 문자열 Parsing
        Queue firstQueue = new LinkedList();
        firstQueue = stringParsing(sNumList);
        System.out.println("----------------------------------------------");
        System.out.println("괄호 1차  => " + firstQueue.toString());

        // 2. 2차 괄호안 계산
        Queue secondQueue = new LinkedList();
        String oneChar;
        while(firstQueue.peek() != null){
            oneChar  = firstQueue.poll().toString();
            //System.out.println("괄호  => " + oneChar);

            if ("()".indexOf(oneChar) >= 0){
                //괄호안 계산 (firstQueue)
                secondQueue.offer(braketInCal(firstQueue));
            }else{
                secondQueue.offer(oneChar);
            }
        }

        //3. Queue에 저장 된 값 중  곱셈. 나눗셈 계산
        secondQueue = multiplyDivideCal(secondQueue);

        //4. Queue에 저장 된 값 중  덧셈. 뺄샘 계산
        String sResult = addSubtractCal(secondQueue);
        System.out.println("괄호 최종결과 => " + sResult);
        return sResult;
    }

    //( ) 괄호 갯수 검증
    public static boolean checkBracket(String sNumList){
        int count1=0, count2=0;
        for(int i=0; i<sNumList.length(); i++){
            if ("(".equals(sNumList.substring(i, i+1))){
                count1 ++;
            }else if (")".equals(sNumList.substring(i, i+1))){
                count2 ++;
            }
        }

        if (count1 == count2)
            return true;
        else{
            System.out.println("괄호 () 갯수가 Match되지 않습니다.");
            return false;
        }
    }

    //------------------------------------------------------------------------
    // 1차. 문자열 Parsing
    //------------------------------------------------------------------------
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Queue stringParsing(String sNumList){
        String sOneNum = "";  //하나의 숫자열
        Queue firstQueue = new LinkedList();
        for(int i=0; i<sNumList.length(); i++){
            String oneChar = sNumList.substring(i, i+1);
            if ("+-*/()".indexOf(oneChar) >= 0 ){

                // 3+(3  과 같이   4칙연산자와 괄호가 동시에 존재 하는 경우 때문
                if (!"".equals(sOneNum)) firstQueue.offer(sOneNum);
                firstQueue.offer(oneChar);
                sOneNum = "";
            }else{
                sOneNum += oneChar;

                if (i+1 == sNumList.length()){
                    firstQueue.offer(sOneNum);
                }
            }
        }
        return firstQueue;
    }

    //------------------------------------------------------------------------
    // 2차. 괄호 내부 계산 (재귀적 호출)
    //------------------------------------------------------------------------
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static String braketInCal(Queue firstQueue) throws Exception{
        String oneChar;
        //1. 괄호 내부에 존재 하는 값을 Queue에 저장
        Queue secondQueue = new LinkedList();
        while(firstQueue.peek() != null){
            oneChar  = firstQueue.poll().toString();
            if ("(".equals(oneChar)){
                //괄호안 계산 (firstQueue)
                secondQueue.offer(braketInCal(firstQueue));
            }else if (")".equals(oneChar)){
                break; //닫기 괄호가 나오면 stop
            }else{
                secondQueue.offer(oneChar);
            }
        }
        System.out.println("괄호 2차  => " + secondQueue.toString());

        //2. Queue에 저장 된 값 중  곱셈. 나눗셈 계산
        secondQueue = multiplyDivideCal(secondQueue);

        //3. Queue에 저장 된 값 중  덧셈. 뺄샘 계산
        String sResult = addSubtractCal(secondQueue);

        return sResult;
    }


    //------------------------------------------------------------------------
    // 곱셈, 나눗셈 계산
    //------------------------------------------------------------------------
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Queue multiplyDivideCal(Queue inQueue) throws Exception{
        Queue outQueue = new LinkedList();
        String oneChar;
        String sNum1 = "", sNum2 = "", sResult = "";
        String operator = "";
        BigDecimal nResult = new BigDecimal("0");
        while(inQueue.peek() != null){
            oneChar  = inQueue.poll().toString();

            if ("+-".indexOf(oneChar) >= 0 ){
                outQueue.offer(sNum1);
                outQueue.offer(oneChar);
                sNum1 = "";
            }else if ("*/".indexOf(oneChar) >= 0 ) {
                operator = oneChar;
            }else{
                if ("".equals(sNum1)){
                    sNum1 = oneChar;
                }else if ("".equals(sNum2)) {
                    sNum2 = oneChar;
                    if ("*".equals(operator)) {
                        nResult = (new BigDecimal(sNum1)).multiply(new BigDecimal(sNum2));
                    }else if ("/".equals(operator)) {
                        if ("0".equals(sNum2)){
                            throw new Exception("나눗셈 분모에 0 이 존재 합니다.");
                        }

                        nResult = (new BigDecimal(sNum1)).divide(new BigDecimal(sNum2), 6, BigDecimal.ROUND_UP);
                    }
                    sResult  = String.valueOf(nResult);


                    sNum1    = sResult;
                    sNum2    = "";
                    operator = "";
                }
            }

            if (inQueue.peek() == null) outQueue.offer(sNum1);
        }
        return outQueue;
    }


    //------------------------------------------------------------------------
    // 덧셈. 뺄샘 계산
    //------------------------------------------------------------------------
    @SuppressWarnings({ "rawtypes" })
    public static String addSubtractCal(Queue inQueue){
        String operator = "";
        String oneChar  = "";
        BigDecimal nResult  = new BigDecimal(inQueue.poll().toString());
        while(inQueue.peek() != null){
            oneChar  = inQueue.poll().toString();

            if ("+-".indexOf(oneChar) >= 0 ){
                operator = oneChar;
            }else{
                if ("+".equals(operator)){
                    nResult = nResult.add(new BigDecimal(oneChar));
                }else if ("-".equals(operator)){
                    nResult = nResult.subtract(new BigDecimal(oneChar));
                }
                operator = "";
            }
        }
        return nResult.toString();
    }
}
