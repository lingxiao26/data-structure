/**
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 *
 * 示例 1:
 * 输入: s = "egg", t = "add" e->a g->d
 * 输出: true
 *
 * 示例 2:
 * 输入: s = "foo", t = "bar" f->b o->a o->r
 * 输出: false
 *
 * 示例 3:
 * 输入: s = "paper", t = "title" p->t a->i p->t e->l r->e
 * 输出: true
 *
 * s = "ab", t = "cc"
 * a->c b->c
 * c->a c->b
 *
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 */
class Solution_205 {
    public boolean isIsomorphic(String s, String t) {
        char[] chs1 = s.toCharArray();
        char[] chs2 = t.toCharArray();

        for ( int i = 0; i < s.length(); i ++ ) {
            if ( s.indexOf(chs1[i]) != t.indexOf(chs2[i]) )
                return false;
        }

        return true;
    }
}