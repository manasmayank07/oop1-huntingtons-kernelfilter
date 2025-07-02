public class KernelFilter {

    // Returns a new picture that is a Gaussian blur of the original
    public static Picture gaussian(Picture picture) {
        double[][] kernel = {
            {1, 2, 1},
            {2, 4, 2},
            {1, 2, 1}
        };
        return kernel(picture, kernel);
    }

    // Returns a new picture that is a Laplacian of the original
    public static Picture laplacian(Picture picture) {
        double[][] kernel = {
            { -1, -1, -1 },
            { -1,  8, -1 },
            { -1, -1, -1 }
        };
        return kernel(picture, kernel);
    }

    // Returns a new picture that is a sharpened version of the original
    public static Picture sharpen(Picture picture) {
        double[][] kernel = {
            { 0, -1,  0 },
            { -1, 5, -1 },
            { 0, -1,  0 }
        };
        return kernel(picture, kernel);
    }

    // Returns a new picture that is the original enhanced with an edge detection kernel
    public static Picture emboss(Picture picture) {
        double[][] kernel = {
            { -2, -1, 0 },
            { -1, 1, 1 },
            { 0, 1, 2 }
        };
        return kernel(picture, kernel);
    }

    // Convolution kernel helper
    public static Picture kernel(Picture picture, double[][] kernel) {
        int width = picture.width();
        int height = picture.height();
        Picture result = new Picture(width, height);
        int kSize = kernel.length;
        int kOffset = kSize / 2;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double r = 0, g = 0, b = 0;
                for (int i = 0; i < kSize; i++) {
                    for (int j = 0; j < kSize; j++) {
                        int xi = Math.min(Math.max(x + i - kOffset, 0), width - 1);
                        int yj = Math.min(Math.max(y + j - kOffset, 0), height - 1);
                        Color color = picture.get(xi, yj);
                        r += color.getRed() * kernel[i][j];
                        g += color.getGreen() * kernel[i][j];
                        b += color.getBlue() * kernel[i][j];
                    }
                }
                r = Math.min(255, Math.max(0, r));
                g = Math.min(255, Math.max(0, g));
                b = Math.min(255, Math.max(0, b));
                result.set(x, y, new Color((int) r, (int) g, (int) b));
            }
        }
        return result;
    }

    // main method for testing (optional)
    public static void main(String[] args) {
        Picture picture = new Picture(args[0]);
        picture.show();

        Picture gaussian = gaussian(picture);
        gaussian.show();

        Picture laplacian = laplacian(picture);
        laplacian.show();

        Picture sharpen = sharpen(picture);
        sharpen.show();

        Picture emboss = emboss(picture);
        emboss.show();
    }
}
