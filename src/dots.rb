def dotsToN(d)
  d.size()
end

def nToDots(n)
  "."*n
end

def addDots(da, db)
  a = dotsToN(da)
  b = dotsToN(db)
  nToDots(a + b)
end

p dotsToN("...")
p nToDots(3)
p addDots("...", "..")

# Monad Version

def dot_result(n)
  "."*n
end

def dot_bind(d, &f)
  f.call(d.size())
end

def dot_add(da, db)
  dot_bind(da) do |a|
    dot_bind(db) do |b|
      dot_result(a+b)
    end
  end
end

p "monad version"
p dot_add("...", "....")