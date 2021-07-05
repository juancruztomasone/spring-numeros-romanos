package com.example.numeroromanosspring.controllers;
import com.example.numeroromanosspring.models.Numbers;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/convert")
public class ConvertController {
    Map<Character, Integer> roman = new HashMap<Character,Integer>();
    private final AtomicLong counter = new AtomicLong();

    public ConvertController() {
        roman.put('I', 1);
        roman.put('V', 5);
        roman.put('X', 10);
        roman.put('L', 50);
        roman.put('C', 100);
        roman.put('D', 500);
        roman.put('M', 1000);
    }

    @GetMapping("/decimal-to-roman")
    public Numbers decimalToRoman(@RequestParam(value = "number", defaultValue = "0") Integer number){
        String m[] = {"", "M", "MM", "MMM"};
        String c[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String x[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String i[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        String thousands = m[number/1000];
        String hundereds = c[(number%1000)/100];
        String tens = x[(number%100)/10];
        String ones = i[number%10];

        String romanNumber = thousands + hundereds + tens + ones;

        return new Numbers(counter.incrementAndGet(),romanNumber,number);
    }

    @GetMapping("/roman-to-decimal")
    public Numbers romanToDecimal(@RequestParam(value = "number") String number){
        int intNumber = 0;
        int n = number.length();

        for(int i = 0; i < n; i++){
            if (i != n - 1 && roman.get(number.charAt(i)) < roman.get(number.charAt(i + 1))){
                intNumber += roman.get(number.charAt(i + 1)) - roman.get(number.charAt(i));
                i++;
            }else{
                intNumber += roman.get(number.charAt(i));
            }
        }

        return new Numbers(counter.incrementAndGet(),number,intNumber);
    }


}
