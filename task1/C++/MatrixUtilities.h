#include <vector>

class MatrixMultiplication {
public:

    class Operands{
    public:
        Operands();
        int n;
        std::vector<std::vector<double>> a;
        std::vector<std::vector<double>> b;
        std::vector<std::vector<double>> c;


    };

    std::vector<std::vector<double>>execute(Operands operands);

};

class MatrixBenchmarking{
public:
    std::vector<double> metrics;
    void test(MatrixMultiplication multiplicador,MatrixMultiplication::Operands op,int rounds, int iterations);

};