# Multithreaded programs

Calculates the value of the integral using the trapezoidal method in multi-threaded mode

<h3 align = "center">
Integral
</h3>

```math
\int_{0}^{\pi / 3} \cos(4t) \cos(2t) dt
```

<h3 align = "center">
Trapezoidal method formula
</h3>

```math
\int_{a}^{b}f(x)dx \approx \frac{f(x_{0}) + f(x_{n})}{2}h + \sum_{i = 1}^{n-1}f(x_{i})h
```

```math
h = \frac{b - a}{n}, x_{i} = a + i * h
```