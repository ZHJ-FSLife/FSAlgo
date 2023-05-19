### 距离公式

- 欧式距离

$$
d(x, y)
= \sqrt{ \sum\limits_{i=1}^{n}(x_i - y_i)^2 }
$$

- 汉明距离
- 曼哈顿距离

$$
d(x, y)
= \sum\limits_{i=1}^{n} |x_i - y_i|
$$

- 切比雪夫距离

$$
d(x, y)
= max(|x_i - y_i|)
$$

- 余弦距离/相似度

$$
cos(x,y)
= \dfrac{ x \times y} {||\vec x|| \times ||\vec y|| }
= \dfrac{ \sum\limits_{i=1}^{n} x_i y_i }
{
  \sqrt{\sum\limits_{i=1}^{n} x^2_i} 
  \sqrt{\sum\limits_{i=1}^{n} y^2_i}
}
$$

### 面积公式

- 以坐标计算不规则图形的面积
  
  $$
  \dfrac{1}{2} \sum\limits_{i=1}^n x_i ( y_{i+1} - y_{i-1} )
  $$



