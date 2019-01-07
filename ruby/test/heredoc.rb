#!/usr/bin/ruby
print <<-EOF
    This is the first way of creating
    here document ie. multiple line string.
EOF

print <<-"EOF";                # save with above
    This is the second way of creating
    here document ie. multiple line string.
EOF

print <<`EOC`                 # exec command
	echo hi there
	echo lo there
EOC

