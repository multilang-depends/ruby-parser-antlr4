#!/usr/bin/ruby
# -*- coding: UTF-8 -*-
 
$i = 0
$num = 5
 
while $i < $num  do
   puts("in loop i = #$i" )
   $i +=1
end


#!/usr/bin/ruby
# -*- coding: UTF-8 -*-
 
$i = 0
$num = 5
begin
   puts("in loop i = #$i" )
   $i +=1
end while $i < $num

puts("in loop i = #$i" ) while $i < $num


#!/usr/bin/ruby
# -*- coding: UTF-8 -*-
 
$i = 0
$num = 5
 
until $i > $num  do
   puts("in loop i = #$i" )
   break
   break
   redo
   next
   $i +=1;
end

puts("in loop i = #$i" ) until $i < $num

#!/usr/bin/ruby
# -*- coding: UTF-8 -*-
 
for i in 0..5
   puts "var= #{i}"
end

(0..5).each do |i|
   puts "var= #{i}"
end

