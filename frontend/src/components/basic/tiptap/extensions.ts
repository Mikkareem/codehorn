import BaseHeading from "@tiptap/extension-heading";
import {CommandProps, mergeAttributes, Node} from "@tiptap/core";

type Levels = 1 | 2 | 3 | 4 | 5 | 6

const classes: Record<Levels, string> = {
    1: 'text-4xl',
    2: 'text-3xl',
    3: 'text-2xl',
    4: 'text-xl',
    5: 'text-lg',
    6: 'text-md'
}

export const Heading = BaseHeading.extend({
    renderHTML({ node, HTMLAttributes }) {
        const hasLevel = this.options.levels.includes(node.attrs.level)
        const level: Levels = hasLevel ? node.attrs.level : this.options.levels[0]

        return [
            `h${level}`,
            mergeAttributes(this.options.HTMLAttributes, HTMLAttributes, {
                class: `${classes[level]}`,
            }),
            0,
        ]
    },
})

declare module '@tiptap/core' {
    interface Commands<ReturnType> {
        pre: {
            setPre: () => ReturnType,
            togglePre: () => ReturnType,
        }
    }
}

type PreOptions = {
    HTMLAttributes: Record<string, unknown>
}

export const Pre = Node.create<PreOptions>({
    name: 'live',
    group: 'block',
    content: 'inline*',


    addOptions() {
        return {
            HTMLAttributes: {}
        }
    },

    renderHTML({ HTMLAttributes }) {
        return [
            'div',
            mergeAttributes(this.options.HTMLAttributes, HTMLAttributes),
            [
                'live-feed',
                {},
                0,
            ]
        ]
    },

    parseHTML() {
        return [
            {
                tag: 'div',
                preserveWhitespace: 'full'
            }
        ]
    },

    addCommands() {
        return {
            setPre: () => ({ commands }: CommandProps) => {
                return commands.setNode(this.name)
            },
            togglePre: () => ({ commands }: CommandProps) => {
                return commands.toggleNode(this.name, 'paragraph')
            },
        }
    }
})
