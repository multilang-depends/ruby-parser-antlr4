#!/usr/bin/ruby
# -*- coding: UTF-8 -*-

    def to_s; writer.string; end
 
def test(a1="Ruby", a2="Perl")
   puts "programming lanaguage is #{a1}"
   puts "programming lanaguage is #{a2}"
end

def test1(a1, a2)
   puts "programming lanaguage is #{a1}"
   puts "programming lanaguage is #{a2}"
end
test "C", "C++"
def test
   i = 100
   j = 200
   k = 300
   return i,j,k
end
var = test
puts var

def sample (*test)
   puts "arg numer= #{test.length}"
   for i in 0...test.length
      puts "arg= #{test[i]}"
   end
end
sample "Zara", "6", "F"
sample "Mac", "36", "M", "MCA"

    def remote_protocol=(new_remote_protocol)
      @remote_protocol = new_remote_protocol
      REMOTE_PROTOCOLS[transport.remote_name] = remote_protocol
    end

    def remote_hash=(new_remote_hash)
      @remote_hash = new_remote_hash
      REMOTE_HASHES[transport.remote_name] = remote_hash
    end

