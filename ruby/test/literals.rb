#!/usr/bin/ruby
 
puts "Hello World!";

puts 1;

puts 1+1-1*1/1**1;

puts 1.0-1.2

puts 123; 
puts 1_123;
puts -500; 
puts 0377;
puts 12345678901234567890;
puts 0xff;
puts 0.211;
puts -0.211;
puts 0b1011; 
puts "a".ord; 
puts ?\n; 
puts 'h'


puts 123.4;
puts 1.0e6; 
puts 4E20; 
puts 4e+20; 
 
#浮点型 
puts 0.0 
puts 2.1 
#puts 1000000.

#string literals
puts 'escape using "\\"';
puts 'That\'s right';

"MUL : #{24*60*60}";

desc1 = %Q{Ruby string contains '' and ""}
desc2 = %q|Ruby string contains '' and ""|

# hash
colors = { "red" => 0xf00, "green" => 0x0f0, "blue" => 0x00f }

# array
array=["fred", 10, 3.14, "This is a string", "last element",]

#range
puts (a..b)
