#include <vector>
#include <cstdlib>
#include <iostream>
#include <algorithm>
#include <numeric>
#include <sys/time.h>
#include "MatrixUtilities.h"

void::MatrixBenchmarking::test(MatrixMultiplication multiplicador,MatrixMultiplication::Operands op,int rounds, int iterations) {
    
    std::vector<double> metrics;
    double diff ;

    for (int r = 0; r < rounds; r++) {
        for (int i = 0; i < iterations; i++) {
            
            struct timeval start, stop;

            gettimeofday(&start,NULL);
            multiplicador.execute(op);
            gettimeofday(&stop,NULL);
            diff = stop.tv_sec - start.tv_sec + 1e-6*(stop.tv_usec - start.tv_usec);
            metrics.push_back(diff);
        }
    }

    double suma = std::accumulate(metrics.begin(), metrics.end(), 0.0);

    double media = suma / metrics.size();

    auto minimo = std::min_element(metrics.begin(), metrics.end());
    auto maximo = std::max_element(metrics.begin(), metrics.end());

    printf("average: %0.6f\n",media);
    printf("min: %0.6f\n",*minimo);
    printf("max:%0.6f\n",*maximo);


};

