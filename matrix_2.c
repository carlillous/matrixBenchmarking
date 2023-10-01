#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <float.h>

#define n 2048
double a[n][n];
double b[n][n];
double c[n][n];

void multiplication(){
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            for (int k = 0; k < n; ++k) {
                c[i][j] = a[i][k] * b[k][j];
             }
        }
    }

}

void benchmark(int iterations){
    double results[iterations];
    double diff ;
    double *ptr = results;
    double minimo = DBL_MAX;
    double maximo = -DBL_MAX; 
    double suma;

    for (int i = 0; i < iterations; i++) {
        struct timeval start, stop;
        gettimeofday(&start,NULL);
        multiplication();
        gettimeofday(&stop,NULL);
        diff = stop.tv_sec - start.tv_sec + 1e-6*(stop.tv_usec - start.tv_usec);
        ptr[i] = diff;

    }

    for (int i = 0; i < iterations; i++) {
        suma += results[i];
         if (results[i] < minimo) {
            minimo = results[i];
        }
        if (results[i] > maximo) {
            maximo = results[i];
        }
    }

    double media = (double)suma / iterations;

    printf("min: %.6f\n", minimo);
    printf("mean: %.6f\n", media);
    printf("max: %.6f\n", maximo);


}

int main() {
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            a[i][j] = (double) rand() / RAND_MAX;
            b[i][j] = (double) rand() / RAND_MAX;
            c[i][j] = 0;
        }
    }
    benchmark(12);
}



